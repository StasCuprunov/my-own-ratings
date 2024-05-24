import {useGet} from "../../interface/useGet";
import {API_ROUTING_RATINGS_CREATE} from "../../constant/routing/APIConstants";

export const useCreateRatingInfo = () => {
    return useGet(API_ROUTING_RATINGS_CREATE);
};

export const getLabelNameProps = (): any => {
    return {
        htmlFor: "name",
        text: "Name",
        sup: "1, 2"
    };
};

export const getLabelDescriptionProps = (): any => {
    return {
        htmlFor: "description",
        text: "Description"
    };
};

export const getLabelMinimumProps = () : any => {
    return {
        htmlFor: "minimum",
        text: "Minimum",
        sup: "1"
    };
};

export const getLabelMaximumProps = () : any => {
    return {
        htmlFor: "maximum",
        text: "Maximum",
        sup: "1"
    };
};

export const getLabelStepWidthProps = () : any => {
    return {
        htmlFor: "step-width",
        text: "Step width",
        sup: "1"
    };
};

export const getInputNameProps = (value: string, maxLength: number, handleChange: any) : any => {
    return {
        required: true,
        name: "name",
        type: "text",
        value: value,
        maxLength: maxLength,
        onChange: handleChange
    };
};

export const getTextAreaDescription = (value: string, maxLength: number, handleChange: any) : any => {
    return {
        name: "description",
        maxLength: maxLength,
        value: value,
        onChange: handleChange
    }
};

export const getInputMinimum = (minFromInput: number, maxFromInput: number, stepFromInput: number, value: number,
                                handleChange: any) : any => {
    return {
        required: true,
        name: "minimum",
        min: minFromInput,
        max: maxFromInput,
        step: stepFromInput,
        value: value,
        onChange: handleChange
    };
};

export const getInputMaximum = (minFromInput: number, maxFromInput: number, stepFromInput: number, value: number,
                                handleChange: any) : any => {
    return {
        required: true,
        name: "maximum",
        min: minFromInput,
        max: maxFromInput,
        step: stepFromInput,
        value: value,
        onChange: handleChange
    };
};

export const getInputStepWidth = (minFromInput: number, maxFromInput: number, stepFromInput: number, value: number,
                                handleChange: any) : any => {
    return {
        required: true,
        name: "step-width",
        min: minFromInput,
        max: maxFromInput,
        step: stepFromInput,
        value: value,
        onChange: handleChange
    };
};