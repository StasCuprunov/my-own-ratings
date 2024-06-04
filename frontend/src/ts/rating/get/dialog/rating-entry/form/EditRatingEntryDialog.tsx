import {FunctionComponent} from "react";
import EditIcon from '@mui/icons-material/Edit';

import {RatingEntryFormDialog} from "./RatingEntryFormDialog";

export const EditRatingEntryDialog: FunctionComponent<any> = ({props}) => {
    const ratingEntryFormDialogProps: any = {
        ...props,
        isEdit: true,
        title: "Edit the rating entry " + props.ratingEntry.name,
        icon: EditIcon,
        submitButtonText: "Edit"
    };

    return (
        <RatingEntryFormDialog props={ratingEntryFormDialogProps}/>
    );
};