package club.archdev.archutilities.utils.menusystem;

import club.archdev.archutilities.utils.ItemBuilder;
import club.archdev.archutilities.utils.Utils;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class ItemStackButton {

    private String name;
    private List<String> lore;
    private Material material;
    private int data;
    private int amount;

    public ItemStack makeItemStack() {
        return new ItemBuilder(this.material).name(Utils.translate(this.name)).lore(Utils.translate(this.lore)).durability(this.data).amount(this.amount).build();
    }
}
