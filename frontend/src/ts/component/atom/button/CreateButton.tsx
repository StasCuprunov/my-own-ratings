import {FunctionComponent} from "react";
import {Button} from "./Button";

export const CreateButton: FunctionComponent<any> = () => {
    const props: any = {
        type: "submit",
        text: "Create"
    };
    return (
        <Button props={props}/>
    );
};