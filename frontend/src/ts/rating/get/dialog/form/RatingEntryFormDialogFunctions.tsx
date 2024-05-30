import {RatingEntry} from "../../../../model/RatingEntry";
import {getInputNumberProps} from "../../../../utility/FormUtility";

export const getSubmitButtonProps = (text: string) => {
    return {
        type: "submit",
        text: text
    };
};

export const getCancelButtonProps = (handleClose: Function) => {
    return {
        type: "button",
        text: "Cancel",
        onClick: handleClose
    };
};

export const getLabelNameProps = (): any => {
    return {
        htmlFor: "name",
        text: "Name",
        sup: "1, 2"
    };
};

export const getLabelValueProps = (): any => {
    return {
        htmlFor: "value",
        text: "Value",
        sup: "1, 3"
    };
};

export const getInputValueProps = (min: number, max: number, step: number, value: number, handleChange: any): any => {
    return getInputNumberProps("value", true, min, max, step, value, handleChange);
};

export const hasAlreadyRatingEntryWithName = (name: string, ratingEntries: RatingEntry[]): boolean => {
    let foundRatingEntries: RatingEntry[] = ratingEntries.filter(entry => entry.name === name);
    return (foundRatingEntries.length > 0);
};