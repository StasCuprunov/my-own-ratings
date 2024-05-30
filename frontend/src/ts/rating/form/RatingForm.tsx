import {FunctionComponent, useMemo, useState} from "react";
import {
    getDefaultRangeOfValues,
    getInputMaximum,
    getInputMinimum,
    getInputNameProps,
    getInputStepWidth,
    getTextAreaDescription,
    getTitle
} from "./RatingFormFunctions";
import {useNavigate} from "react-router-dom";
import {getSmallestPositiveNumberWithNumberOfDecimalDigits} from "../../utility/MathUtility";
import {Rating} from "../../model/Rating";
import {InputValidation} from "../../model/InputValidation";
import {handleChange} from "../../utility/FormUtility";
import {RatingDTO} from "../../dto/RatingDTO";
import {createRating} from "./create/CreateRatingFunctions";
import {getWebsiteRoutingRatingsById} from "../../constant/routing/WebsiteRoutingConstants";
import {RatingFormPage} from "./RatingFormPage";
import {RangeOfValues} from "../../model/RangeOfValues";
import {editRating} from "./edit/EditRatingFunctions";
import {mapRatingDTOToRating} from "../../utility/MapperUtility";

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
    const [scaleValidation, setScaleValidation] =
        useState(new InputValidation());

    const [backendError, setBackendError] = useState(null);

    const handleRatingChange = (field: string) => {
        return handleChange(field, setRating);
    };

    const handleRangeOfValuesChange = (field: string) => {
        return handleChange(field, setRangeOfValues);
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

    const handleMinimumBlur = () => {
        let condition: boolean = false;
        let text: string = "";
        if (isMinimumTooBig()) {
            condition = true;
            text = "Minimum must be really smaller than maximum.";
        }
        setMinimumValidation({
            condition: condition,
            text: text
        });
        checkIfTheValuesCreateAScale();
    };

    const handleMaximumBlur = () => {
        let condition: boolean = false;
        let text: string = "";
        if (isMinimumTooBig()) {
            condition = true;
            text = "Maximum must be really bigger than minimum.";
        }
        setMaximumValidation({
            condition: condition,
            text: text
        });
        checkIfTheValuesCreateAScale();
    };

    const handleStepWidthBlur = () => {
        checkIfTheValuesCreateAScale();
    };

    const checkIfTheValuesCreateAScale = () => {
        let condition: boolean = false;
        let text: string = "";
        if (!isScale()) {
            condition = true;
            text = "With the specified attributes it does not build a scale.";
        }
        setScaleValidation({
            condition: condition,
            text: text
        });
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
            scaleValidation.condition);
    };

    const isScale = (): boolean => {
        return Number.isInteger((rangeOfValues.maximum - rangeOfValues.minimum) / rangeOfValues.stepWidth);
    };

    const isMinimumTooBig = (): boolean => {
        return (rangeOfValues.minimum - rangeOfValues.maximum) >= 0;
    };

    const inputName: any = useMemo(() =>
            getInputNameProps(rating.name, props.maximumLengthOfName, handleRatingChange("name"), handleNameBlur),
        [rating.name]);
    const textAreaDescription: any = useMemo(() => getTextAreaDescription(rating.description,
        props.maximumLengthOfDescription, handleRatingChange("description")), [rating.description]);
    const inputMinimum: any = useMemo(() =>
        getInputMinimum(rangeOfValuesMinimumBorder, rangeOfValuesMaximumBorder, step, rangeOfValues.minimum,
            handleRangeOfValuesChange("minimum"), handleMinimumBlur), [rangeOfValues.minimum]);
    const inputMaximum: any = useMemo(() =>
        getInputMaximum(rangeOfValuesMinimumBorder, rangeOfValuesMaximumBorder, step, rangeOfValues.maximum,
            handleRangeOfValuesChange("maximum"), handleMaximumBlur), [rangeOfValues.maximum]);
    const inputStepWidth: any = useMemo(() =>
        getInputStepWidth(step, rangeOfValuesMaximumBorder, step, rangeOfValues.stepWidth,
            handleRangeOfValuesChange("stepWidth"), handleStepWidthBlur), [rangeOfValues.stepWidth]);

    const propsPage: any = {
        isEdit: isEdit,
        id: ratingDTO?.id,
        title: getTitle(isEdit, props.ratingDTO?.name),
        maximumNumberOfDecimalDigits: maximumNumberOfDecimalDigits,
        inputName: inputName,
        textAreaDescription: textAreaDescription,
        inputMinimum: inputMinimum,
        inputMaximum: inputMaximum,
        inputStepWidth: inputStepWidth,
        handleSubmit: handleSubmit,
        nameValidation: nameValidation,
        minimumValidation: minimumValidation,
        maximumValidation: maximumValidation,
        scaleValidation: scaleValidation,
        backendError: backendError
    };

    return (
        <RatingFormPage props={propsPage}/>
    );
};