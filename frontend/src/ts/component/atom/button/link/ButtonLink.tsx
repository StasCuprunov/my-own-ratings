import {FunctionComponent} from "react";
import {Link} from "react-router-dom";
import {Button} from "../Button";

export const ButtonLink: FunctionComponent<any> = ({props}) => {
    const button: any = {
        type: props.type,
        text: props.text
    };
    return (
        <Link to={props.to}><Button props={button}/></Link>
    );
};