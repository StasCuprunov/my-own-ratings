import {FunctionComponent} from "react";
import {Link} from "react-router-dom";
import {CustomButton} from "../CustomButton";

export const ButtonLink: FunctionComponent<any> = ({props}) => {
    const button: any = {
        type: props.type,
        variant: props.variant,
        text: props.text
    };
    return (
        <Link to={props.to}><CustomButton props={button}/></Link>
    );
};