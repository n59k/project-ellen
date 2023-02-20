package sk.tuke.kpi.oop.game.items;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import sk.tuke.kpi.gamelib.ActorContainer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class Backpack implements ActorContainer<Collectible> {
    private final String name;
    private int capacity;

    public Backpack(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
    }

    private List<Collectible> BackpackContain = new ArrayList<>();
    @Override
    public @NotNull List<Collectible> getContent() {
        return List.copyOf(BackpackContain);
    }

    @Override
    public int getCapacity() {
        return this.capacity;
    }

    @Override
    public int getSize() {
        return BackpackContain.size();
    }

    @Override
    public @NotNull String getName() {
        return this.name;
    }

    @Override
    public void add(@NotNull Collectible actor) {
        if (getSize() > getCapacity() || getSize() == getCapacity()) {
            throw new IllegalStateException(getName() + " is max");
        }
        else if (getSize() < getCapacity()){
            BackpackContain.add(actor);
        }else {
            throw new IllegalStateException();
        }
    }
    public void setBackpackSize(int sizeUp){
        this.capacity += sizeUp;
    }

    @Override
    public void remove(@NotNull Collectible actor) {
        if (BackpackContain == null) {
            return;
        }else {
            BackpackContain.remove(actor);
        }
    }



    @Override
    public @Nullable Collectible peek() {
        int BackSize = getSize();
        if (BackSize <= 0) {
            return null;
        }
        else{
            return BackpackContain.get(BackSize-1);
        }

    }

    @Override
    public void shift() {
        Collections.rotate(BackpackContain, 1);
    }

    @NotNull
    @Override
    public Iterator<Collectible> iterator() {
        return BackpackContain.iterator();
    }
}
