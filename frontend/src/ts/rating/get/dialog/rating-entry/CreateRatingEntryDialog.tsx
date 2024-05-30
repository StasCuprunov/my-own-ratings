import {FunctionComponent} from "react";
import {RatingEntryFormDialog} from "./RatingEntryFormDialog";
import {RatingEntry} from "../../../../model/RatingEntry";

export const CreateRatingEntryDialog: FunctionComponent<any> = ({props}) => {
    const ratingEntryFormDialogProps: any = {
        ...props,
        title: "Create a new rating entry",
        ratingEntry: new RatingEntry(undefined, props.ratingId, undefined, props.rangeOfValues.minimum),
        submitButtonText: "Create"
    };

    return (
        <RatingEntryFormDialog props={ratingEntryFormDialogProps}/>
    );
};