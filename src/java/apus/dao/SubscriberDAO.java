/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package apus.dao;

import apus.entity.Subscriber;
import java.util.List;

/**
 * @author  Maxim Vasilevsky
 * @author  Roman Dyatkovsky
 * @since APUS v0.2
 */
public interface SubscriberDAO<TypeEntity extends Subscriber> extends AbstractDAO <TypeEntity> {
    List<TypeEntity> readByName (String name, int type);
}