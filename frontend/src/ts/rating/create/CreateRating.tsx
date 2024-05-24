import {FunctionComponent, useMemo, useState} from "react";
import {CreateRatingPage} from "./CreateRatingPage";
import {
    getTextAreaDescription,
    getInputMaximum,
    getInputMinimum,
    getInputNameProps,
    getInputStepWidth
} from "./CreateRatingFunctions";
import {Rating} from "../../model/Rating";
import {RangeOfValues} from "../../model/RangeOfValues";
import {getSmallestPositiveNumberWithNumberOfDecimalDigits} from "../../utility/MathUtility";
import {handleChange} from "../../utility/FormUtility";

export const CreateRating: FunctionComponent<any> = ({props}) => {
    const rangeOfValuesMinimumBorder: number = props.rangeOfValuesMinimumBorder;
    const rangeOfValuesMaximumBorder: number = props.rangeOfValuesMaximumBorder;
    const step: number= useMemo(() =>
        getSmallestPositiveNumberWithNumberOfDecimalDigits(props.maximumNumberOfDecimalDigits), []);

    const [rating, setRating] = useState(new Rating(props.userId));
    const [rangeOfValues, setRangeOfValues] = useState(new RangeOfValues());

    const handleRatingChange = (field: string) => {
        return handleChange(field, setRating);
    };

    const handleRangeOfValuesChange = (field: string) => {
        return handleChange(field, setRangeOfValues);
    };

    const inputName: any = useMemo(() =>
        getInputNameProps(rating.name, props.maximumLengthOfName, handleRatingChange("name")), [rating.name]);
    const textAreaDescription: any = useMemo(() => getTextAreaDescription(rating.description,
        props.maximumLengthOfDescription, handleRatingChange("description")), [rating.description]);
    const inputMinimum: any = useMemo(() =>
        getInputMinimum(rangeOfValuesMinimumBorder, rangeOfValuesMaximumBorder, step, rangeOfValues.minimum,
            handleRangeOfValuesChange("minimum")), [rangeOfValues.minimum]);
    const inputMaximum: any = useMemo(() =>
        getInputMaximum(rangeOfValuesMinimumBorder, rangeOfValuesMaximumBorder, step, rangeOfValues.maximum,
            handleRangeOfValuesChange("maximum")), [rangeOfValues.maximum]);
    const inputStepWidth: any = useMemo(() =>
        getInputStepWidth(step, rangeOfValuesMaximumBorder, step, rangeOfValues.stepWidth,
            handleRangeOfValuesChange("stepWidth")), [rangeOfValues.stepWidth]);
    return (
        <CreateRatingPage
            inputName={inputName} textAreaDescription={textAreaDescription} inputMinimum={inputMinimum}
            inputMaximum={inputMaximum} inputStepWidth={inputStepWidth}
        />
    );
};