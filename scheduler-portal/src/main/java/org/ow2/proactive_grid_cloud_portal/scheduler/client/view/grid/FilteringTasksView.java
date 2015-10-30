package org.ow2.proactive_grid_cloud_portal.scheduler.client.view.grid;

import java.util.List;

import org.ow2.proactive_grid_cloud_portal.scheduler.client.SchedulerListeners.TasksUpdatedListener;
import org.ow2.proactive_grid_cloud_portal.scheduler.client.Task;
import org.ow2.proactive_grid_cloud_portal.scheduler.client.controller.TasksController;

import com.smartgwt.client.widgets.layout.Layout;

public class FilteringTasksView extends FilteringGridItemView implements TasksUpdatedListener{

    protected TasksController controller;

    
    public FilteringTasksView(TasksController controller) {
        this.controller = controller;
        this.controller.getModel().addTasksUpdatedListener(this);
        this.itemName = "tasks";
    }
    
    @Override
    public void tasksUpdating() {
        this.itemUpdating();
    }

    @Override
    public void tasksUpdated(List<Task> tasks, long totalTasks) {
        this.itemUpdated();
    }

    @Override
    public void tasksUpdatedFailure(String message) {
        this.itemUpdatedFailure(message);
    }

    @Override
    protected Layout buildToolbar() {
        return this.controller.getTaskNavigationController().buildView();
    }

    @Override
    protected Layout buildPagination() {
        return this.controller.getTaskNavigationController().getPaginationController().buildView();
    }

    @Override
    protected void buildGrid() {
        this.itemsGrid = new TasksListGrid(this.controller);
        this.itemsGrid.build();
    }
}
