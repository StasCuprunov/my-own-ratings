import {FunctionComponent} from "react";
import {CustomButton} from "./CustomButton";
import {CSS_CLASS_CREATE_BUTTON} from "../../../constant/CSSClassNameConstants";
import AddCircleOutlineIcon from "@mui/icons-material/AddCircleOutline";

export const CreateButton: FunctionComponent<any> = () => {
    const props: any = {
        type: "submit",
        text: "Create",
        icon: AddCircleOutlineIcon,
        className: CSS_CLASS_CREATE_BUTTON
    };
    return (
        <CustomButton props={props}/>
    );
};