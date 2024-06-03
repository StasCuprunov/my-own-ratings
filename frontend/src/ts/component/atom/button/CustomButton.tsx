import {FunctionComponent} from "react";
import Button from "@mui/material/Button";

export const CustomButton: FunctionComponent<any> = ({props}) => {
  let type: any = (props.type) ? props.type : "button";
  let variant: any = (props.variant) ? props.variant : "contained";

  return (
      <Button type={type} variant={variant} onClick={props.onClick}>{props.text}</Button>
  );
};