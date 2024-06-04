import {FunctionComponent} from "react";
import AddCircleOutlineIcon from '@mui/icons-material/AddCircleOutline';

import {RatingEntryFormDialog} from "./RatingEntryFormDialog";
import {RatingEntry} from "../../../../../model/rating-entry/RatingEntry";

export const CreateRatingEntryDialog: FunctionComponent<any> = ({props}) => {
    const ratingEntryFormDialogProps: any = {
        ...props,
        title: "Create a new rating entry",
        ratingEntry: new RatingEntry(undefined, props.ratingId, undefined, props.rangeOfValues.minimum),
        openButtonText: "Create rating entry",
        icon: AddCircleOutlineIcon,
        submitButtonText: "Create"
    };

    return (
        <RatingEntryFormDialog props={ratingEntryFormDialogProps}/>
    );
};