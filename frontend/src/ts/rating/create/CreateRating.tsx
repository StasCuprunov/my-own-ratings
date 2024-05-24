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

    const [minimumValidation, setMinimumValidation] =
        useState(new InputValidation());
    const handleRatingChange = (field: string) => {
        return handleChange(field, setRating);
    };

    const handleRangeOfValuesChange = (field: string) => {
        return handleChange(field, setRangeOfValues);
    };

    const handleMinimumBlur = () => {
        if (rangeOfValues.minimum >= rangeOfValues.maximum) {
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

    const handleSubmit = (event: any) => {
        event.preventDefault();
        if (minimumValidation.condition) {
            return;
        }
    };

    const inputName: any = useMemo(() =>
        getInputNameProps(rating.name, props.maximumLengthOfName, handleRatingChange("name")), [rating.name]);
    const textAreaDescription: any = useMemo(() => getTextAreaDescription(rating.description,
        props.maximumLengthOfDescription, handleRatingChange("description")), [rating.description]);
    const inputMinimum: any = useMemo(() =>
        getInputMinimum(rangeOfValuesMinimumBorder, rangeOfValuesMaximumBorder, step, rangeOfValues.minimum,
            handleRangeOfValuesChange("minimum"), handleMinimumBlur), [rangeOfValues.minimum]);
    const inputMaximum: any = useMemo(() =>
        getInputMaximum(rangeOfValuesMinimumBorder, rangeOfValuesMaximumBorder, step, rangeOfValues.maximum,
            handleRangeOfValuesChange("maximum")), [rangeOfValues.maximum]);
    const inputStepWidth: any = useMemo(() =>
        getInputStepWidth(step, rangeOfValuesMaximumBorder, step, rangeOfValues.stepWidth,
            handleRangeOfValuesChange("stepWidth")), [rangeOfValues.stepWidth]);
    return (
        <CreateRatingPage
            inputName={inputName} textAreaDescription={textAreaDescription} inputMinimum={inputMinimum}
            inputMaximum={inputMaximum} inputStepWidth={inputStepWidth} handleSubmit={handleSubmit}
            minimumValidation={minimumValidation}
        />
    );
};