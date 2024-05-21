export const setIdAttribute = (id?: string) => {
    return (id != null) ? id : null;
}
export const setStringAttribute = (attribute?: string) => {
    return (attribute == null) ? "" : attribute;
};