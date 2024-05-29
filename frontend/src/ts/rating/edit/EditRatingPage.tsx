import {FunctionComponent} from "react";
import {
    getLabelDescriptionProps,
    getLabelMaximumProps,
    getLabelMinimumProps,
    getLabelNameProps, getLabelStepWidthProps
} from "../RatingFormUtility";

const labelName: any = getLabelNameProps();
const labelDescription: any = getLabelDescriptionProps();
const labelMinimum: any = getLabelMinimumProps();
const labelMaximum: any = getLabelMaximumProps();
const labelStepWidth: any = getLabelStepWidthProps();

export const EditRatingPage: FunctionComponent<any> = ({name, description, handleSubmit}) => {
    return (
        <div>
            <h1>Edit the rating {name}</h1>
            <form onSubmit={handleSubmit}>

            </form>
        </div>
    );
};