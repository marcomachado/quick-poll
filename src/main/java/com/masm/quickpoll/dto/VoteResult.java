package com.masm.quickpoll.dto;

import java.util.Collection;

public class VoteResult {
    private int totalVotes;
    private Collection<OptionCount> result;

    public VoteResult(int totalVotes, Collection<OptionCount> result) {
        this.totalVotes = totalVotes;
        this.result = result;
    }

    public VoteResult() {
    }

    public int getTotalVotes() {
        return totalVotes;
    }

    public void setTotalVotes(int totalVotes) {
        this.totalVotes = totalVotes;
    }

    public Collection<OptionCount> getResult() {
        return result;
    }

    public void setResult(Collection<OptionCount> result) {
        this.result = result;
    }
}
