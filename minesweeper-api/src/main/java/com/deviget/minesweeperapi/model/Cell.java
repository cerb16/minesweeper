package com.deviget.minesweeperapi.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "cell")
public class Cell {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="game_id", referencedColumnName = "id")
    private Game game;

    @Column(name = "x_position")
    private int xPosition;

    @Column(name = "y_position")
    private int yPosition;

    @Column(name = "mine")
    private boolean mine;

    @Column(name = "flag")
    private boolean flag;

    @Column(name = "revealed")
    private boolean revealed;

    @Column(name = "value")
    private int value;
}
