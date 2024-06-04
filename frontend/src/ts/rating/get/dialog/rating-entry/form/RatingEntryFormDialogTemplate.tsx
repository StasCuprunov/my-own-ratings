import {FunctionComponent} from "react";
import Dialog from "@mui/material/Dialog";
import DialogTitle from "@mui/material/DialogTitle";
import DialogContent from "@mui/material/DialogContent";
import DialogContentText from "@mui/material/DialogContentText";
import DialogActions from "@mui/material/DialogActions";
import {CustomButton} from "../../../../../component/atom/button/CustomButton";
import {FormForNumber} from "../../../../../component/molecule/form-attribute/FormForNumber";
import {FormForInput} from "../../../../../component/molecule/form-attribute/FormForInput";
import {getCancelButtonProps, getSubmitButtonProps} from "../../../../../utility/FormUtility";

export const RatingEntryFormDialogTemplate: FunctionComponent<any> = ({props}) => {
    return (
        <div>
            {props.openDialogButton &&
                <CustomButton props={props.openDialogButton}/>
            }
            <Dialog open={props.isOpen} onClose={props.handleClose}>
                <form onSubmit={props.handleSubmit}>
                    <DialogTitle>
                        {props.title}
                    </DialogTitle>
                    <DialogContent>
                        <DialogContentText>
                            <FormForInput props={props.formForName}/>
                            <FormForNumber props={props.formForValue}/>
                            <div>
                                <h4>Hints</h4>
                                <ul>
                                    <li>
                                        <sup>1</sup>Required
                                    </li>
                                    <li>
                                        <sup>2</sup>The name must be unique within the rating.
                                    </li>
                                    <li>
                                        <sup>3</sup>It must be possible to accept the value within the scale.
                                    </li>
                                </ul>
                            </div>
                        </DialogContentText>
                    </DialogContent>
                    <DialogActions>
                        <CustomButton props={getSubmitButtonProps(props.submitButton)}/>
                        <CustomButton props={getCancelButtonProps(props.handleClose)}/>
                    </DialogActions>
                </form>
            </Dialog>
        </div>
    );
};