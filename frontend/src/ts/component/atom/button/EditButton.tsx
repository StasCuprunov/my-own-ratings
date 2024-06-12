import {FunctionComponent} from "react";
import {CustomButton} from "./CustomButton";
import EditIcon from "@mui/icons-material/Edit";
import {CSS_CLASS_EDIT_BUTTON} from "../../../constant/CSSClassNameConstants";

export const EditButton: FunctionComponent<any> = () => {
    const props: any = {
        type: "submit",
        text: "Edit",
        icon: EditIcon,
        className: CSS_CLASS_EDIT_BUTTON
    };
    return (
        <CustomButton props={props}/>
    );
};