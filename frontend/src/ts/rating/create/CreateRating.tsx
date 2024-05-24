import {FunctionComponent, useMemo, useState} from "react";
import {CreateRatingPage} from "./CreateRatingPage";
import {
    getTextAreaDescription,
    getInputMaximum,
    getInputMinimum,
    getInputNameProps,
    getInputStepWidth,
    getDefaultRangeOfValues
} from "./CreateRatingFunctions";
import {Rating} from "../../model/Rating";
import {RangeOfValues} from "../../model/RangeOfValues";
import {getSmallestPositiveNumberWithNumberOfDecimalDigits} from "../../utility/MathUtility";
import {handleChange} from "../../utility/FormUtility";
import {InputValidation} from "../../model/InputValidation";

const defaultRangeOfValues: RangeOfValues = getDefaultRangeOfValues();

export const CreateRating: FunctionComponent<any> = ({props}) => {
    const rangeOfValuesMinimumBorder: number = props.rangeOfValuesMinimumBorder;
    const rangeOfValuesMaximumBorder: number = props.rangeOfValuesMaximumBorder;
    const step: number= useMemo(() =>
        getSmallestPositiveNumberWithNumberOfDecimalDigits(props.maximumNumberOfDecimalDigits), []);

    const [rating, setRating] = useState(new Rating(props.userId));
    const [rangeOfValues, setRangeOfValues] =
        useState(defaultRangeOfValues);

    const [nameValidation, setNameValidation] =
        useState(new InputValidation());
    const [minimumValidation, setMinimumValidation] =
        useState(new InputValidation());
    const [maximumValidation, setMaximumValidation] =
        useState(new InputValidation());
    const [stepWidthValidation, setStepWidthValidation] =
        useState(new InputValidation());

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
        if (isMinimumTooBig()) {
            setMinimumValidation({
                condition: true,
                text: "Minimum must be real smaller than maximum."
            });
        }
        else {
            setMinimumValidation({
                ...minimumValidation,
                condition: false
            });
        }
    };

    const handleMaximumBlur = () => {
        if (isMinimumTooBig()) {
            setMaximumValidation({
                condition: true,
                text: "Maximum must be real bigger than minimum."
            });
        }
        else {
            setMaximumValidation({
                ...maximumValidation,
                condition: false
            })
        }
    };

    const handleStepWidthBlur = () => {
        if (!isScale()) {
            setStepWidthValidation({
                condition: true,
                text: "The specified step width does not create a scale."
            });
        }
        else {
            setStepWidthValidation({
                ...stepWidthValidation,
                condition: false
            });
        }
    };

    const isScale = (): boolean => {
        return Number.isInteger((rangeOfValues.maximum - rangeOfValues.minimum) / rangeOfValues.stepWidth);
    };

    const isMinimumTooBig = (): boolean => {
        return (rangeOfValues.minimum - rangeOfValues.maximum) >= 0;
    };

    const handleSubmit = (event: any) => {
        event.preventDefault();
        if (nameValidation.condition || minimumValidation.condition || maximumValidation.condition ||
            stepWidthValidation.condition) {
            return;
        }
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
            inputName={inputName} textAreaDescription={textAreaDescription} inputMinimum={inputMinimum}
            inputMaximum={inputMaximum} inputStepWidth={inputStepWidth} handleSubmit={handleSubmit}
            nameValidation={nameValidation} minimumValidation={minimumValidation} maximumValidation={maximumValidation}
            stepWidthValidation={stepWidthValidation}
        />
    );
};