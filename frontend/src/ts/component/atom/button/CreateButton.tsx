import {FunctionComponent} from "react";
import {CustomButton} from "./CustomButton";

export const CreateButton: FunctionComponent<any> = () => {
    const props: any = {
        type: "submit",
        text: "Create"
    };
    return (
        <CustomButton props={props}/>
    );
};