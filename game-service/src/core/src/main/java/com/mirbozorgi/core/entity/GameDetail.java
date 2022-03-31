package com.mirbozorgi.core.entity;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "game_detail")
public class GameDetail {

  @Id
  @Access(AccessType.PROPERTY)
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @ManyToOne(fetch = FetchType.LAZY, targetEntity = Game.class)
  @JoinColumn(name = "game_id", foreignKey = @ForeignKey(name = "FK_GAME_DETAIL_GAME"), nullable = false)
  private Game game;

  @Column(name = "public_key", unique = true, nullable = false)
  private String publicKey;

  @Column(name = "private_key", nullable = false)
  private String privateKey;

  @Column(name = "fail_mirror_link", length = 1000)
  private String failMirrorLink;

  @Column(name = "success_mirror_link", length = 1000)
  private String successMirrorLink;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Game getGame() {
    return game;
  }

  public void setGame(Game game) {
    this.game = game;
  }

  public String getPublicKey() {
    return publicKey;
  }

  public void setPublicKey(String publicKey) {
    this.publicKey = publicKey;
  }

  public String getPrivateKey() {
    return privateKey;
  }

  public void setPrivateKey(String privateKey) {
    this.privateKey = privateKey;
  }

  public String getFailMirrorLink() {
    return failMirrorLink;
  }

  public void setFailMirrorLink(String failMirrorLink) {
    this.failMirrorLink = failMirrorLink;
  }

  public String getSuccessMirrorLink() {
    return successMirrorLink;
  }

  public void setSuccessMirrorLink(String successMirrorLink) {
    this.successMirrorLink = successMirrorLink;
  }
}
