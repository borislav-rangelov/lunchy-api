package com.brangelov.lunchy.model;

import org.springframework.data.domain.Page;

import java.util.Collection;
import java.util.List;

public class PageModel<T> {

    private final List<T> collection;
    private final PageInfo page;

    public PageModel(Page<T> page) {
        collection = page.getContent();
        this.page = new PageInfo(page);
    }

    public Collection<T> getCollection() {
        return collection;
    }

    public PageInfo getPage() {
        return page;
    }

    public static class PageInfo {
        private final long totalPages;
        private final long totalElements;
        private final boolean first;
        private final boolean last;
        private final long size;
        private final long number;

        public PageInfo(Page<?> page) {
            totalPages = page.getTotalPages();
            totalElements = page.getTotalElements();
            first = page.isFirst();
            last = page.isLast();
            size = page.getSize();
            number = page.getNumber();
        }

        public long getTotalPages() {
            return totalPages;
        }

        public long getTotalElements() {
            return totalElements;
        }

        public boolean isFirst() {
            return first;
        }

        public boolean isLast() {
            return last;
        }

        public long getSize() {
            return size;
        }

        public long getNumber() {
            return number;
        }
    }
}
