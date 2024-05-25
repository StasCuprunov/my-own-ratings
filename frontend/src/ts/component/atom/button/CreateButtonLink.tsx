import {FunctionComponent} from "react";
import {ButtonLink} from "./ButtonLink";

export const CreateButtonLink: FunctionComponent<any> = ({props}) => {
    const buttonLinkProps: any = {
        ...props,
        type: "button",
        text: "Create"
    }
    return (
        <ButtonLink props={buttonLinkProps}/>
    );
};