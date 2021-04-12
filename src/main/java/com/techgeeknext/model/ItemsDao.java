package com.techgeeknext.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "Items")
@JsonIgnoreProperties({"hibernateLazyIntializer","handler","posted_items"})
public class ItemsDao {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer item_id;
    private String item_name;
    private String description;
    private Integer base_price;
    private Integer req;

    public Integer getReq() {
        return req;
    }

    public void setReq(Integer req) {
        this.req = req;
    }

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "item_id_fk", referencedColumnName = "item_id")
    List<BidsDao> bids  = new ArrayList<BidsDao>();

    @ManyToOne(cascade={CascadeType.ALL})
    @JoinColumn(name="requested_item_id")
    private ItemsDao requested_item;

    @OneToMany(mappedBy="requested_item")
    private Set<ItemsDao> posted_items = new HashSet<ItemsDao>();

    public ItemsDao getRequested_item() {
        return requested_item;
    }

    public void setRequested_item(ItemsDao requested_item) {
        this.requested_item = requested_item;
    }

    public Set<ItemsDao> getPosted_items() {
        return posted_items;
    }

    public void setPosted_items(Set<ItemsDao> posted_items) {
        this.posted_items = posted_items;
    }

    public List<BidsDao> getBids() {
        return bids;
    }

    public void setBids(List<BidsDao> bids) {
        this.bids = bids;
    }

    public Integer getItem_id() {
        return item_id;
    }

    public void setItem_id(Integer item_id) {
        this.item_id = item_id;
    }

    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getBase_price() {
        return base_price;
    }

    public void setBase_price(Integer base_price) {
        this.base_price = base_price;
    }
}

