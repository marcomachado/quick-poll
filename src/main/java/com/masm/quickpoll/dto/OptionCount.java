package com.masm.quickpoll.dto;

import java.util.Objects;

public class OptionCount {
    private Long optionId;
    private int count;

    public OptionCount(Long optionId, int count) {
        this.optionId = optionId;
        this.count = count;
    }

    public OptionCount() {
    }

    public Long getOptionId() {
        return optionId;
    }

    public void setOptionId(Long optionId) {
        this.optionId = optionId;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OptionCount that = (OptionCount) o;
        return count == that.count && Objects.equals(optionId, that.optionId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(optionId, count);
    }
}
