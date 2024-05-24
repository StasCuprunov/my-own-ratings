import {FunctionComponent} from "react";
import {Label} from "../../component/atom/form/Label";
import {Input} from "../../component/atom/form/input/Input";
import {InputNumber} from "../../component/atom/form/input/InputNumber";
import {
    getLabelDescriptionProps,
    getLabelMaximumProps,
    getLabelMinimumProps,
    getLabelNameProps, getLabelStepWidthProps
} from "./CreateRatingFunctions";
import {TextArea} from "../../component/atom/form/TextArea";

const labelName: any = getLabelNameProps();
const labelDescription: any = getLabelDescriptionProps();
const labelMinimum: any = getLabelMinimumProps();
const labelMaximum: any = getLabelMaximumProps();
const labelStepWidth: any = getLabelStepWidthProps();

export const CreateRatingPage: FunctionComponent<any> = ({inputName, textAreaDescription, inputMinimum, inputMaximum,
                                                             inputStepWidth}) => {
    return (
        <div>
            <h1>Create your own rating</h1>
            <form>
                <div>
                    <Label props={labelName}/>
                    <Input props={inputName}/>
                </div>
                <div>
                    <Label props={labelDescription}/>
                    <TextArea props={textAreaDescription}/>
                </div>
                <div>
                    <h2>Scale<sup>3</sup></h2>
                    <div>
                        <Label props={labelMinimum}/>
                        <InputNumber props={inputMinimum}/>
                    </div>
                    <div>
                        <Label props={labelMaximum}/>
                        <InputNumber props={inputMaximum}/>
                    </div>
                    <div>
                        <Label props={labelStepWidth}/>
                        <InputNumber props={inputStepWidth}/>
                    </div>
                </div>
                <div>
                    <h2>Hints</h2>
                    <ul>
                        <li>
                            <sup>1</sup>Required
                        </li>
                        <li>
                            <sup>2</sup>Unique for every rating
                        </li>
                        <li>
                            <sup>3</sup>...
                        </li>
                    </ul>
                </div>
            </form>
        </div>
    );
};