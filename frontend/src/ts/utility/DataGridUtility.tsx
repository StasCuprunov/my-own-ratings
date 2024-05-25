import {DEFAULT_PAGINATION_SIZE} from "../constant/DataGridConstants";

export const customizePaginationDefault = () => {
    return {
        pagination: { paginationModel: { pageSize: DEFAULT_PAGINATION_SIZE } }
    };
};