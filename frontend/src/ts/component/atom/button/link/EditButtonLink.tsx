import {FunctionComponent} from "react";
import {ButtonLink} from "./ButtonLink";

export const EditButtonLink: FunctionComponent<any> = ({to}) => {
    const buttonLinkProps: any = {
        to: to,
        type: "button",
        text: "Edit"
    }
    return (
        <ButtonLink props={buttonLinkProps}/>
    );
};