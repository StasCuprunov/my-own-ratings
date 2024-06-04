import {FunctionComponent} from "react";
import Button from "@mui/material/Button";
import {ICON_SMALL} from "../../../constant/IconConstants";

export const CustomButton: FunctionComponent<any> = ({props}) => {
  let type: any = (props.type) ? props.type : "button";
  let variant: any = (props.variant) ? props.variant : "contained";

  let iconProps: any = props.iconProps;
  let iconFontSize: any = (iconProps?.fontSize) ? iconProps.fontSize : ICON_SMALL;

  return (
      <Button type={type} variant={variant} onClick={props.onClick}>
        {props.text}
        {props.icon &&
          <props.icon fontSize={iconFontSize}/>
        }
      </Button>
  );
};