package by.issoft.store.factory;

import by.issoft.domain.Category;

import java.util.List;

public interface Populator {
    List<Category> createCategoryList();
}
