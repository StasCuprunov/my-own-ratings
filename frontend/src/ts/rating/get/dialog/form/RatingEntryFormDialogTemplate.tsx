import {FunctionComponent} from "react";
import Dialog from "@mui/material/Dialog";
import DialogTitle from "@mui/material/DialogTitle";
import DialogContent from "@mui/material/DialogContent";
import DialogContentText from "@mui/material/DialogContentText";
import DialogActions from "@mui/material/DialogActions";
import {Button} from "../../../../component/atom/button/Button";
import {Label} from "../../../../component/atom/form/Label";
import {Input} from "../../../../component/atom/form/input/Input";
import {InputError} from "../../../../component/atom/form/input/InputError";
import {
    getCancelButtonProps,
    getLabelNameProps,
    getLabelValueProps,
    getSubmitButtonProps
} from "./RatingEntryFormDialogFunctions";
import {InputNumber} from "../../../../component/atom/form/input/InputNumber";

const labelName: any = getLabelNameProps();
const labelValue: any = getLabelValueProps();

export const RatingEntryFormDialogTemplate: FunctionComponent<any> = ({props}) => {
    return (
        <div>
            {props.openDialogButton &&
                <Button props={props.openDialogButton}/>
            }
            <Dialog open={props.isOpen} onClose={props.handleClose}>
                <form onSubmit={props.handleSubmit}>
                    <DialogTitle>
                        {props.title}
                    </DialogTitle>
                    <DialogContent>
                        <DialogContentText>
                            <div>
                                <Label props={labelName}/>
                                <Input props={props.inputName}/>
                                <InputError props={props.nameValidation}/>
                            </div>
                            <div>
                                <Label props={labelValue}/>
                                <InputNumber props={props.inputValue}/>
                            </div>
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
                        <Button props={getSubmitButtonProps(props.submitButtonText)}/>
                        <Button props={getCancelButtonProps(props.handleClose)}/>
                    </DialogActions>
                </form>
            </Dialog>
        </div>
    );
};