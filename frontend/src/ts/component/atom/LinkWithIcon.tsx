import {FunctionComponent} from "react";
import {Link} from "react-router-dom";
import {CSS_CLASS_LINK_WITH_ICON} from "../../constant/CSSClassNameConstants";
import {ICON_SMALL} from "../../constant/IconConstants";

export const LinkWithIcon: FunctionComponent<any> = ({props}) => {
    let className: string = (props.className) ? props.className : CSS_CLASS_LINK_WITH_ICON;

    let iconProps: any = props.iconProps;
    let iconFontSize: any = (iconProps?.fontSize) ? iconProps.fontSize : ICON_SMALL;

    return (
        <Link className={className} to={props.to}>
            {props.text}
            {props.icon &&
                <props.icon fontSize={iconFontSize}/>
            }
        </Link>
    )
};