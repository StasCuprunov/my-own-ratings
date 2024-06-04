import {FunctionComponent} from "react";
import {CustomButton} from "../../../../../component/atom/button/CustomButton";
import Dialog from "@mui/material/Dialog";
import DialogTitle from "@mui/material/DialogTitle";
import DialogActions from "@mui/material/DialogActions";
import {
    getCancelButtonProps,
    getDeleteButtonInDialogProps
} from "../../../../../utility/FormUtility";

export const DeleteRatingEntryDialogTemplate: FunctionComponent<any> = ({props}) => {

    return (
        <div>
            <Dialog open={props.isOpen} onClose={props.handleClose}>
                <DialogTitle>
                    Do you really want to delete the rating entry "{props.name}"?
                </DialogTitle>
                <DialogActions>
                    <CustomButton props={getDeleteButtonInDialogProps(props.handleDelete)}/>
                    <CustomButton props={getCancelButtonProps(props.handleClose)}/>
                </DialogActions>
            </Dialog>
        </div>
    );
};