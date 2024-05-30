import {FunctionComponent} from "react";
import {RatingEntryFormDialog} from "./RatingEntryFormDialog";

export const EditRatingEntryDialog: FunctionComponent<any> = ({props}) => {
    const ratingEntryFormDialogProps: any = {
        ...props,
        isEdit: true,
        title: "Edit the rating entry " + props.ratingEntry.name,
        submitButtonText: "Edit"
    };

    return (
        <RatingEntryFormDialog props={ratingEntryFormDialogProps}/>
    );
};