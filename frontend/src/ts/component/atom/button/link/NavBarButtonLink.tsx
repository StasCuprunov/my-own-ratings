import {FunctionComponent} from "react";
import {ButtonLink} from "./ButtonLink";

export const NavBarButtonLink: FunctionComponent<any> = ({props}) => {
    const buttonLink: any = {
        ...props,
        variant: "outlined"
    };
    return (
        <ButtonLink props={buttonLink}/>
    );
};