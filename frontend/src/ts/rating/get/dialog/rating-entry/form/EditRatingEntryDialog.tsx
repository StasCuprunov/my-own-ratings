import {FunctionComponent} from "react";
import EditIcon from '@mui/icons-material/Edit';

import {RatingEntryFormDialog} from "./RatingEntryFormDialog";
import {CSS_CLASS_EDIT_BUTTON_DIALOG} from "../../../../../constant/CSSClassNameConstants";

export const EditRatingEntryDialog: FunctionComponent<any> = ({props}) => {
    const ratingEntryFormDialogProps: any = {
        ...props,
        isEdit: true,
        title: "Edit the rating entry ",
        icon: EditIcon,
        submitButton: {
            text: "Edit",
            className: CSS_CLASS_EDIT_BUTTON_DIALOG
        }
    };

    return (
        <RatingEntryFormDialog props={ratingEntryFormDialogProps}/>
    );
};