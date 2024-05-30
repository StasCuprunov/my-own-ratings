export const getDeleteOpenButton = (handleOpenDialog: Function) => {
    return {
        type: "button",
        text: "Delete",
        onClick: handleOpenDialog
    }
};
export const getDeleteDialogButton = (handleDelete: Function) => {
    return {
        type: "button",
        text: "Delete",
        onClick: handleDelete
    }
};