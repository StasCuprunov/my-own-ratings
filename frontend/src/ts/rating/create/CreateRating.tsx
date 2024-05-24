import {FunctionComponent, useMemo, useState} from "react";
import {CreateRatingPage} from "./CreateRatingPage";
import {
    getTextAreaDescription,
    getInputMaximum,
    getInputMinimum,
    getInputNameProps,
    getInputStepWidth,
    getDefaultRangeOfValues,
    createRating
} from "./CreateRatingFunctions";
import {Rating} from "../../model/Rating";
import {RangeOfValues} from "../../model/RangeOfValues";
import {getSmallestPositiveNumberWithNumberOfDecimalDigits} from "../../utility/MathUtility";
import {handleChange} from "../../utility/FormUtility";
import {InputValidation} from "../../model/InputValidation";
import {RatingDTO} from "../../dto/RatingDTO";
import {useNavigate} from "react-router-dom";
import {getWebsiteRoutingRatingsById} from "../../constant/routing/WebsiteRoutingConstants";

const defaultRangeOfValues: RangeOfValues = getDefaultRangeOfValues();

export const CreateRating: FunctionComponent<any> = ({props}) => {
    const navigate = useNavigate();
    const maximumNumberOfDecimalDigits: number = props.maximumNumberOfDecimalDigits;
    const rangeOfValuesMinimumBorder: number = props.rangeOfValuesMinimumBorder;
    const rangeOfValuesMaximumBorder: number = props.rangeOfValuesMaximumBorder;
    const step: number= useMemo(() =>
        getSmallestPositiveNumberWithNumberOfDecimalDigits(maximumNumberOfDecimalDigits), []);

    const [rating, setRating] = useState(new Rating(undefined, props.userId));
    const [rangeOfValues, setRangeOfValues] =
        useState(defaultRangeOfValues);

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
    }

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

    const isScale = (): boolean => {
        return Number.isInteger((rangeOfValues.maximum - rangeOfValues.minimum) / rangeOfValues.stepWidth);
    };

    const isMinimumTooBig = (): boolean => {
        return (rangeOfValues.minimum - rangeOfValues.maximum) >= 0;
    };

    const handleSubmit = async (event: any) => {
        event.preventDefault();
        if (nameValidation.condition || minimumValidation.condition || maximumValidation.condition ||
            scaleValidation.condition) {
            return;
        }
        let ratingDTO: RatingDTO = new RatingDTO(undefined, rating.userId, rating.name, rating.description,
            rangeOfValues);

        const {data, error} = await createRating(ratingDTO);
        if (error) {
            setBackendError(error);
            return;
        }
        navigate(getWebsiteRoutingRatingsById(data.id));
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
    return (
        <CreateRatingPage
            maximumNumberOfDecimalDigits={maximumNumberOfDecimalDigits} inputName={inputName}
            textAreaDescription={textAreaDescription} inputMinimum={inputMinimum}
            inputMaximum={inputMaximum} inputStepWidth={inputStepWidth} handleSubmit={handleSubmit}
            nameValidation={nameValidation} minimumValidation={minimumValidation} maximumValidation={maximumValidation}
            scaleValidation={scaleValidation} backendError={backendError}
        />
    );
};