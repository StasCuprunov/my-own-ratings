import {FunctionComponent} from "react";
import {Button} from "./Button";

export const EditButton: FunctionComponent<any> = () => {
    const props: any = {
        type: "submit",
        text: "Edit"
    };
    return (
        <Button props={props}/>
    );
};