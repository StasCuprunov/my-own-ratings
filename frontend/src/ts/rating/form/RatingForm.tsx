import {FunctionComponent, useMemo, useState} from "react";
import {
    getDefaultRangeOfValues,
    getInputMaximum,
    getInputMinimum,
    getInputNameProps,
    getInputStepWidth,
    getLabelDescriptionProps,
    getLabelMaximumProps,
    getLabelMinimumProps,
    getLabelNameProps,
    getLabelStepWidthProps,
    getTextAreaDescription,
    getTitle
} from "./RatingFormFunctions";
import {useNavigate} from "react-router-dom";
import {getSmallestPositiveNumberWithNumberOfDecimalDigits} from "../../utility/MathUtility";
import {Rating} from "../../model/Rating";
import {InputValidation} from "../../model/InputValidation";
import {handleChange, handleChangeWithValue} from "../../utility/FormUtility";
import {RatingDTO} from "../../dto/RatingDTO";
import {createRating} from "./create/CreateRatingFunctions";
import {getWebsiteRoutingRatingsById} from "../../constant/routing/WebsiteRoutingConstants";
import {RatingFormPage} from "./RatingFormPage";
import {RangeOfValues} from "../../model/RangeOfValues";
import {editRating} from "./edit/EditRatingFunctions";
import {mapRatingDTOToRating} from "../../utility/MapperUtility";
import {
    CSS_CLASS_DESCRIPTION,
    CSS_CLASS_MAXIMUM,
    CSS_CLASS_MINIMUM,
    CSS_CLASS_NAME,
    CSS_CLASS_STEP_WIDTH
} from "../../constant/CSSClassNameConstants";
import {PageTemplate} from "../../component/PageTemplate";

const exactMath = require("exact-math");

const labelName: any = getLabelNameProps();
const labelDescription: any = getLabelDescriptionProps();
const labelMinimum: any = getLabelMinimumProps();
const labelMaximum: any = getLabelMaximumProps();
const labelStepWidth: any = getLabelStepWidthProps();

export const RatingForm: FunctionComponent<any> = ({props}) => {
    const isEdit: boolean = props.isEdit;
    const ratingDTO: any = props.ratingDTO;
    const maximumNumberOfDecimalDigits: number = props.maximumNumberOfDecimalDigits;
    const rangeOfValuesMinimumBorder: number = props.rangeOfValuesMinimumBorder;
    const rangeOfValuesMaximumBorder: number = props.rangeOfValuesMaximumBorder;

    const initializeRangeOfValues: RangeOfValues = (isEdit) ? ratingDTO.rangeOfValues : getDefaultRangeOfValues();
    const initializeRating: Rating = (isEdit) ? mapRatingDTOToRating(ratingDTO) :
        new Rating(undefined, props.userId);

    const navigate = useNavigate();
    const step: number = useMemo(() =>
        getSmallestPositiveNumberWithNumberOfDecimalDigits(maximumNumberOfDecimalDigits), []);

    const [rating, setRating] = useState(initializeRating);
    const [rangeOfValues, setRangeOfValues] =
        useState(initializeRangeOfValues);

    const [nameValidation, setNameValidation] =
        useState(new InputValidation());

    const [minimumValidation, setMinimumValidation] =
        useState(new InputValidation());
    const [maximumValidation, setMaximumValidation] =
        useState(new InputValidation());
    const [stepWidthValidation, setStepWidthValidation] =
        useState(new InputValidation());

    const [backendError, setBackendError] = useState(null);

    const handleRatingChange = (field: string) => {
        return handleChange(field, setRating);
    };

    const handleRangeOfValuesChange = (field: string, value: number) => {
        return handleChangeWithValue(field, value, setRangeOfValues);
    };

    const handleMinimumChange = (value: number) => {
        return handleRangeOfValuesChange("minimum", value);
    };
    const handleMaximumChange = (value: number) => {
        return handleRangeOfValuesChange("maximum", value);
    };
    const handleStepWidthChange = (value: number) => {
        return handleRangeOfValuesChange("stepWidth", value);
    };

    const handleNameBlur = () => {
        let condition: boolean = false;
        let text: string = "";
        if (!rating.name.trim()) {
            condition = true;
            text = "The name may not be empty.";
        }
        setNameValidation({
            condition: condition,
            text: text
        });
    };

    const handleScaleBlur = () => {
        setMaximumError();
        setStepWidthError();
        setMinimumError();
    };

    const handleSubmit = async (event: any) => {
        event.preventDefault();
        if (areAttributesNotValid()) {
            return;
        }

        let id: string = (isEdit) ? ratingDTO.id : undefined;
        let newRatingDTO: RatingDTO = new RatingDTO(id, rating.userId, rating.name, rating.description, rangeOfValues);

        const {data, error} = (isEdit) ? await editRating(newRatingDTO) : await createRating(newRatingDTO);

        if (error) {
            setBackendError(error);
            return;
        }
        navigate(getWebsiteRoutingRatingsById(data.id));
    };

    const areAttributesNotValid = () => {
        return (nameValidation.condition || minimumValidation.condition || maximumValidation.condition ||
            stepWidthValidation.condition);
    };

    const isScale = (): boolean => {
        return Number.isInteger(
            exactMath.div(
                exactMath.sub(rangeOfValues.maximum, rangeOfValues.minimum),
                rangeOfValues.stepWidth)
        );
    };

    const isMinimumBiggerThanMaximum = (): boolean => {
        return exactMath.sub(rangeOfValues.minimum, rangeOfValues.maximum) >= 0;
    };

    const setMinimumError = () => {
        let condition: boolean = false;
        let text: string = "";

        if (!isMinimumExistent()) {
            condition = true;
            text = "Minimum is required.";
        }
        else if (isValueTooSmall(rangeOfValues.minimum)) {
            condition = true;
            text = "Minimum must be greater or equal " + rangeOfValuesMinimumBorder + ".";
        }
        else if (isValueTooBig(rangeOfValues.minimum)) {
            condition = true;
            text = "Minimum must be smaller or equal " + rangeOfValuesMaximumBorder + ".";
        }

        if (!condition && isMaximumExistent() && isMinimumBiggerThanMaximum()) {
            condition = true;
            text = "Minimum must be really smaller than maximum.";
        }

        if (!condition && isMaximumExistent() && isStepWidthExistent() && !isScale()) {
            condition = true;
            text = "With the specified attributes it does not build a scale.";
        }

        setMinimumValidation({
            condition: condition,
            text: text
        });
    };

    const setMaximumError = () => {
        let condition: boolean = false;
        let text: string = "";

        if (!isMaximumExistent()) {
            condition = true;
            text = "Maximum is required.";
        }
        else if (isValueTooSmall(rangeOfValues.maximum)) {
            condition = true;
            text = "Maximum must be greater equal " + rangeOfValuesMinimumBorder + ".";
        }
        else if (isValueTooBig(rangeOfValues.maximum)) {
            condition = true;
            text = "Maximum must be smaller equal " + rangeOfValuesMaximumBorder + ".";
        }

        setMaximumValidation({
            condition: condition,
            text: text
        });
    };

    const setStepWidthError = () => {
        let condition: boolean = false;
        let text: string = "";

        if (!isStepWidthExistent()) {
            condition = true;
            text = "Step width is required.";
        }
        else if (isStepWidthTooSmall()) {
            condition = true;
            text = "Step width must be greater or equal " + step + ".";
        }

        setStepWidthValidation({
            condition: condition,
            text: text
        });
    };

    const isMinimumExistent = (): boolean => {
        return (rangeOfValues.minimum !== undefined) && (rangeOfValues.minimum !== null);
    }

    const isMaximumExistent = (): boolean => {
        return (rangeOfValues.maximum !== undefined) && (rangeOfValues.maximum !== null);
    };

    const isStepWidthExistent = (): boolean => {
        return (rangeOfValues.stepWidth !== undefined) && (rangeOfValues.stepWidth !== null);
    };

    const isValueTooSmall = (value: number): boolean => {
        return exactMath.sub(value, rangeOfValuesMinimumBorder) < 0;
    };

    const isValueTooBig = (value: number): boolean => {
        return exactMath.sub(value, rangeOfValuesMaximumBorder) > 0;
    };

    const isStepWidthTooSmall = (): boolean => {
        return rangeOfValues.stepWidth < step;
    }

    const inputName: any = useMemo(() =>
            getInputNameProps(rating.name, props.maximumLengthOfName, handleRatingChange("name"), handleNameBlur),
        [rating.name]);
    const textAreaDescription: any = useMemo(() => getTextAreaDescription(rating.description,
        props.maximumLengthOfDescription, handleRatingChange("description")), [rating.description]);
    const inputMinimum: any = useMemo(() =>
        getInputMinimum(step, rangeOfValues.minimum, handleMinimumChange, handleScaleBlur,
            maximumNumberOfDecimalDigits), [rangeOfValues]);
    const inputMaximum: any = useMemo(() =>
        getInputMaximum(step, rangeOfValues.maximum, handleMaximumChange, handleScaleBlur,
            maximumNumberOfDecimalDigits), [rangeOfValues]);
    const inputStepWidth: any = useMemo(() =>
        getInputStepWidth(step, rangeOfValues.stepWidth, handleStepWidthChange, handleScaleBlur,
            maximumNumberOfDecimalDigits), [rangeOfValues]);

    const formForName: any = {
        label: labelName,
        input: inputName,
        inputError: nameValidation,
        className: CSS_CLASS_NAME
    };

    const formForDescription: any = {
        label: labelDescription,
        textArea: textAreaDescription,
        className: CSS_CLASS_DESCRIPTION
    };

    const formForMinimum: any = {
        label: labelMinimum,
        inputNumber: inputMinimum,
        inputError: minimumValidation,
        className: CSS_CLASS_MINIMUM
    };

    const formForMaximum: any = {
        label: labelMaximum,
        inputNumber: inputMaximum,
        inputError: maximumValidation,
        className: CSS_CLASS_MAXIMUM
    };

    const formForStepWidth: any = {
        label: labelStepWidth,
        inputNumber: inputStepWidth,
        inputError: stepWidthValidation,
        className: CSS_CLASS_STEP_WIDTH
    };

    const pageProps: any = {
        isEdit: isEdit,
        id: ratingDTO?.id,
        title: getTitle(isEdit),
        oldName: (isEdit) ? initializeRating.name : null,
        maximumNumberOfDecimalDigits: maximumNumberOfDecimalDigits,
        formForName: formForName,
        formForDescription: formForDescription,
        formForMinimum: formForMinimum,
        formForMaximum: formForMaximum,
        formForStepWidth: formForStepWidth,
        handleSubmit: handleSubmit,
        backendError: backendError,
    };

    return (
        <PageTemplate Component={RatingFormPage} props={pageProps}/>
    );
};