import {ChangeEvent} from "react";

export const handleChange: any = (field: string, setObject: any) => {
    return (e: ChangeEvent<HTMLInputElement>) => {
        setObject((prev: any) => ({
            ...prev,
            [field]: e.target.value
        }));
    };
};