import {FunctionComponent} from "react";
import {Button} from "../../../../../component/atom/button/Button";
import Dialog from "@mui/material/Dialog";
import DialogTitle from "@mui/material/DialogTitle";
import DialogActions from "@mui/material/DialogActions";
import {getCancelButtonProps, getDeleteButtonProps} from "../../../../../utility/FormUtility";

export const DeleteRatingEntryDialogTemplate: FunctionComponent<any> = ({props}) => {

    return (
        <div>
            <Dialog open={props.isOpen} onClose={props.handleClose}>
                <DialogTitle>
                    Do you really want to delete the rating entry "{props.name}"?
                </DialogTitle>
                <DialogActions>
                    <Button props={getDeleteButtonProps(props.handleDelete)}/>
                    <Button props={getCancelButtonProps(props.handleClose)}/>
                </DialogActions>
            </Dialog>
        </div>
    );
};