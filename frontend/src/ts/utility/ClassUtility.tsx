export const setIdAttribute = (id?: string) : string | null => {
    return (id != null) ? id : null;
}
export const setStringAttribute = (attribute?: string): string => {
    return (attribute == null) ? "" : attribute;
};

export const setNumberAttribute = (attribute?: number): number => {
    return (attribute == null) ? 0.0 : attribute;
};

export const setBooleanAttribute = (attribute?: boolean): boolean => {
    return (attribute == null) ? false : attribute;
};