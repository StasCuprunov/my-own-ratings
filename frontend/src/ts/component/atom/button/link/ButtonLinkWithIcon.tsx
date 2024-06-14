import {FunctionComponent} from "react";
import {Link} from "react-router-dom";
import {CustomButton} from "../CustomButton";

export const ButtonLinkWithIcon: FunctionComponent<any> = ({props}) => {
    const buttonWithIcon: any = {
        type: props.type,
        variant: props.variant,
        text: props.text,
        icon: props.icon,
        iconProps: props.iconProps,
        className: props.className
    };

    return (
        <Link to={props.to}><CustomButton props={buttonWithIcon}/></Link>
    );
};