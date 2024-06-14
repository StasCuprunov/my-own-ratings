import {FunctionComponent} from "react";
import Dialog from "@mui/material/Dialog";
import DialogTitle from "@mui/material/DialogTitle";
import DialogContent from "@mui/material/DialogContent";
import DialogContentText from "@mui/material/DialogContentText";
import DialogActions from "@mui/material/DialogActions";
import {CustomButton} from "../../../../component/atom/button/CustomButton";
import {getCancelButtonProps} from "../../../../utility/FormUtility";

export const DeleteRatingDialogTemplate: FunctionComponent<any> = ({props}) => {
    return (
        <div>
            <CustomButton props={props.deleteOpenButton}/>
            <Dialog open={props.isOpen} onClose={props.handleClose}>
                <DialogTitle>
                    Do you really want to delete the rating <i>{props.name}</i>?
                </DialogTitle>
                <DialogContent>
                    <DialogContentText>
                        If you delete the rating then all rating entries belongs to this are also deleted.
                    </DialogContentText>
                </DialogContent>
                <DialogActions>
                    <CustomButton props={props.deleteDialogButton}/>
                    <CustomButton props={getCancelButtonProps(props.handleClose)}/>
                </DialogActions>
            </Dialog>
        </div>
    );
};