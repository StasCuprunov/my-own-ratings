import {FunctionComponent} from "react";

export const Button: FunctionComponent<any> = ({props}) => {
  return (
      <button type={props.type}>{props.text}</button>
  );
};