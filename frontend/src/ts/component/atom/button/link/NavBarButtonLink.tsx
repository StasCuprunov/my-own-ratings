import {FunctionComponent} from "react";
import {ButtonLinkWithIcon} from "./ButtonLinkWithIcon";

export const NavBarButtonLink: FunctionComponent<any> = ({props}) => {
    const buttonLinkWithIconProps: any = {
        ...props,
        variant: "outlined"
    };
    return (
        <ButtonLinkWithIcon props={buttonLinkWithIconProps}/>
    );
};