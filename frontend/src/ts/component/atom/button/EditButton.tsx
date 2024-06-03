import {FunctionComponent} from "react";
import {CustomButton} from "./CustomButton";

export const EditButton: FunctionComponent<any> = () => {
    const props: any = {
        type: "submit",
        text: "Edit"
    };
    return (
        <CustomButton props={props}/>
    );
};