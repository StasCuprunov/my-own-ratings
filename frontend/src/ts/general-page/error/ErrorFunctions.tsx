import ReplayIcon from '@mui/icons-material/Replay';

export const getReloadButtonProps = (handleClick: Function) => {
    return {
        text: "Reload page",
        onClick: handleClick,
        icon: ReplayIcon
    };
};
export const getDescription = (error: any): string => {
    let description: string = "";

    if (isNetworkError(error)) {
        description = "The backend server is probably not available."
    }
    else if (isAxiosError(error)) {
        description = getDescriptionFromBackendError(error);
    }
    return description;
};

const isNetworkError = (error: any): boolean => {
    return error.message === "Network Error";
};

const isAxiosError = (error: any): boolean => {
    return error.name === "AxiosError";
};

const getDescriptionFromBackendError = (error: any): string => {
    let data: any = error.response.data
    if (data.error) {
        return data.error;
    }
    return data;
};
