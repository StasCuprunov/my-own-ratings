import {FunctionComponent} from "react";
import Dialog from '@mui/material/Dialog';
import DialogActions from '@mui/material/DialogActions';
import DialogContent from '@mui/material/DialogContent';
import DialogContentText from '@mui/material/DialogContentText';
import DialogTitle from '@mui/material/DialogTitle';

import {Button} from "../../component/atom/button/Button";

export const DeleteRatingDialog: FunctionComponent<any> = ({props}) => {
    const deleteButtonProps: any = {
        type: "button",
        text: "Delete",
        onClick: props.deleteHandleOnClick
    };

    const cancelButtonProps: any = {
        type: "button",
        text: "Cancel",
        onClick: props.cancelHandleOnClick
    };

    return (
        <Dialog open={props.isOpen} onClose={props.handleClose}>
            <DialogTitle>
                Do you really want to delete the rating?
            </DialogTitle>
            <DialogContent>
                <DialogContentText>
                    If you delete the rating then all rating entries belongs to this are also deleted.
                </DialogContentText>
            </DialogContent>
            <DialogActions>
                <Button props={deleteButtonProps}/>
                <Button props={cancelButtonProps}/>
            </DialogActions>
        </Dialog>
    );
};