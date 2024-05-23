import {FunctionComponent} from "react";

export const Button: FunctionComponent<any> = ({props}) => {
  let type: any = (props.type) ? props.type : "button";
  return (
      <button type={type}>{props.text}</button>
  );
};