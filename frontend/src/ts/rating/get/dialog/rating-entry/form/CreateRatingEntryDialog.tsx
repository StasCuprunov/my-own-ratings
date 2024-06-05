import {FunctionComponent} from "react";
import AddCircleOutlineIcon from '@mui/icons-material/AddCircleOutline';

import {RatingEntryFormDialog} from "./RatingEntryFormDialog";
import {RatingEntry} from "../../../../../model/rating-entry/RatingEntry";
import {CSS_CLASS_CREATE_BUTTON} from "../../../../../constant/CSSClassNameConstants";

export const CreateRatingEntryDialog: FunctionComponent<any> = ({props}) => {
    const ratingEntryFormDialogProps: any = {
        ...props,
        title: "Create a new rating entry",
        ratingEntry: new RatingEntry(undefined, props.ratingId, undefined, props.rangeOfValues.minimum),
        openButtonText: "Create rating entry",
        icon: AddCircleOutlineIcon,
        submitButton: {
            text: "Create",
            className: CSS_CLASS_CREATE_BUTTON
        }
    };

    return (
        <RatingEntryFormDialog props={ratingEntryFormDialogProps}/>
    );
};